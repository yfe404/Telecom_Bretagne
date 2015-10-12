Fs = 1000;            % Sampling frequency
T = 1/Fs;             % Sampling period
L = 100;             % Length of signal
t = (1:L)*T;        % Time vector
tau = 400;
alpha = 0.2;


% Form a signal containing a 50 Hz sinusoid of amplitude 0.7...  
e = sin(2*pi*50*t) ;

%plot(1000*t(1:50),e(1:50))

% and a 50 Hz sinusoid 
s_tau =  alpha * sin(2*pi*50*(t-tau)) ;

% Corrupt the signal with zero-mean white noise with a variance of 4.
%X = S + 2*randn(size(t));

r = s_tau + e;

%plot(t, e, 'b');
%hold on ;
%plot(t, s_tau, 'r');


R = fft(r);
 
H = 1 + alpha * exp(-2 * i *  pi * (1./(t) * tau));
%H_1 = (1 ./ abs(H)) .* exp(-i.*arg(H));






%plot(t, ifft(R.*H_1));
plot(t, ifft(R./H));
hold on;
plot(t, e, 'r');


