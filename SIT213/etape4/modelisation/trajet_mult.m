Fs = 1000;            % Sampling frequency
T = 1/Fs;             % Sampling period
L = 100;             % Length of signal
t = (1:L)*T;        % Time vector
tau = 60;
alpha = 0.2;
k = 2;


nSamplesPerBit = 30; % number of samples per bit 
numericSignalLength = 10000; % length of the numerical signal 

amplMin = 0; % min ampl of the NRZ signal 
amplMax = 1.2; % max ampl of the NRZ signal 

numericalSignal = floor(mod((randn(1,numericSignalLength)), 2)); % Generate a random numerical signal

NRZSignal = nrz(numericalSignal, nSamplesPerBit, numericSignalLength, amplMin, amplMax);

%plot(NRZSignal)
r =  zeros(1, length(NRZSignal)+tau) ;

for i=1:(tau + length(NRZSignal))
  if i <= tau  
    r(1,i) = NRZSignal(1,i);
  elseif i > length(NRZSignal)
    r(1,i) = NRZSignal(1,i-tau);
  else
    r(1,i) = NRZSignal(1,i);
    r(1,i) +=  NRZSignal(1,i-tau);
  endif
end


%plot(r);


%% Reconstruction d'un signal numérique
numericRecu = zeros(1, length(r)/nSamplesPerBit);
for i=1:length(numericRecu)
  val = (sum(r((i-1)*30+1:i*30)))/30;
  
  if val > (abs(amplMax) - abs(amplMin))/2
    numericRecu(i) = amplMax;
  else  
    numericRecu(i) = amplMin;
  endif
 end
 
 

%r = s_tau + e;

% Corrupt the signal with zero-mean white noise with a variance of 4.
%r = r + randn(size(t));

%plot(t, e, 'b');
%hold on ;
%plot(t, s_tau, 'r');


R = fft(r);
 t = 1:1:300060;
H = 1 + alpha * exp(-2 * i *  pi * (1./t) * tau);
H_1 = (1 ./ abs(H)) .* exp(-i.*arg(H));



y = ifft(R./H);

%for i=2:length(y)-1
% y(i) = (1/9)*(4*y(i-1) + y(i) + 4*y(i+1));
%end

s_filtre = ifft(R.*H_1);
%plot(t,s_filtre );
%plot(t,y , 'b');
%hold on;
%plot(t, e, 'r'); 


s_filtre_n = moyennage (s_filtre, nSamplesPerBit, 0.5);


TEB=  sum(s_filtre_n != numericalSignal) / numericSignalLength


