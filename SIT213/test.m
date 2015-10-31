nSamplesPerBit = 30; % number of samples per bit 
numericSignalLength = 10; % length of the numerical signal 
30
amplMin = 0; % min ampl of the NRZ signal 
amplMax = 1.2; % max ampl of the NRZ signal 

t = 0; 
tau = 30; % durée d'un symbole
k = 0; 

%% Création du signal numérique 
numericalSignal = floor(mod((randn(1,numericSignalLength)), 2));
numericalSignal;


% Création du signal analogique 
%signalEmis = zeros(1, tau * length(numericSignalLength));
b = 0.1*randn(1,numericSignalLength*nSamplesPerBit);

NRZTSignal = nrzt(numericalSignal, nSamplesPerBit, numericSignalLength, 0, 1);


%%%% TESTS %%%%
numericalSignal = [ 0 1 0 1 0 1 0 1 0 1]
NRZTSignal = nrzt(numericalSignal, nSamplesPerBit, numericSignalLength, 0, 1);


rows = 2;
cols = 2;


p = 1;
A = [0.9 ];
T = [105 ];


TF_SIGNAL_EMIS = abs(fft(NRZTSignal));


signal = NRZTSignal;

taille_trame = length(signal);
trame1 = zeros(1, taille_trame);
trame2 = zeros(1, taille_trame);




% Trajet direct
[s,r] = multipath(signal, 0, taille_trame);
trame1 += s;
trame2 += r;

emis = trame1 + trame2;

figure;
subplot(rows,cols,1);
plot(abs(fft(trame1)))
title ('FFT SIGNAL EMIS');
subplot(rows,cols,2);
plot(abs(fft(trame2)))



% Multi trajet
[s,r] = multipath(A(1)*signal, T(1), taille_trame);
trame1 += s;
trame2 += r;

subplot(rows,cols,3);
plot(abs(fft(trame1)))
title ('FFT SIGNAL POST CANAL');
subplot(rows,cols,4);
plot(abs(fft(trame2)));

resultante = trame1 + trame2; 

ffr = fft(resultante);


for i=1 : length(ffr)
if ffr(i) == 0
ffr(i) = 1;
endif;
end

 

filtre_reception = fft(emis) ./ ffr

signal_recu = filtre_reception .*ffr;

figure
plot(abs(ifft(signal_recu)));



TF_SIGNAL_POST_CANAL = abs(fft(resultante));



numericalSignal = floor(mod((randn(1,numericSignalLength)), 2));
numericalSignal
NRZTSignal = nrzt(numericalSignal, nSamplesPerBit, numericSignalLength, 0, 1);


trame1 = zeros(1, taille_trame);
trame2 = zeros(1,taille_trame);
% Trajet direct
[s,r] = multipath(signal, 0, taille_trame);
trame1 += s;
trame2 += r;
% Multi trajet
[s,r] = multipath(A(1)*signal, T(1), taille_trame);
trame1 += s;
trame2 += r;
resultante = trame1 + trame2; 


fft_RECU = fft(resultante); 
signal_recu = filtre_reception .*fft_RECU;

figure
plot(abs(ifft(signal_recu)));

moyenneur(abs(ifft(signal_recu)), 10, 30, 1);


%%%% END TESTS %%%%

signal = NRZTSignal ;

p = 1;
A = [0.5];
T = [10];

b = randn(1,numericSignalLength*nSamplesPerBit);
taille_trame = length(signal);

trame1 = zeros(1, taille_trame);
trame2 = zeros(1, taille_trame);







%%%%% FIGURE %%%%%%%



p = 1;
A = [0.9 ];
T = [105 ];


% Nombre de lignes
rows = 2 + p;

% Nombre de colonnes
cols = 2;


%% Signal traj direct
figure
[s,r] = multipath(signal, 0, taille_trame);
subplot(rows,cols,1) ;
plot(s)
title (sprintf ('Trajet direct', cols));
subplot(rows,cols,2);
plot(r)
trame1 += s;
trame2 += r;

%% Signal traj multiples 

for i = 1:p
[s,r] = multipath(A(i)*signal, T(i), taille_trame);
subplot(rows,cols,i*2+1);
plot(s)
title (sprintf ('multipath no %d, offset %d atten %.2f', i, T(i), A(i)));

subplot(rows,cols,(i+1)*2);
plot(r);

trame1 += s;
trame2 += r;

end


%% Résultante

subplot(rows,cols,p*2+3);
plot(trame1);
title (sprintf ('Signal reçu', i, T(i), A(i)));


subplot(rows,cols,p*2+4);
plot(trame2);




figure 
plot(egaliseur([trame1 trame2], p, T))

moyenneur(egaliseur([trame1 trame2], p, T), numericSignalLength, nSamplesPerBit, 1.1)

teb = sum ( moyenneur(egaliseur([trame1 trame2], p, T), numericSignalLength, nSamplesPerBit, 1.1) - numericalSignal) /numericSignalLength

