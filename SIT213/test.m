nSamplesPerBit = 30; % number of samples per bit 
numericSignalLength = 500; % length of the numerical signal 
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
plot(filtered);
title (sprintf ('Signal reçu', i, T(i), A(i)));


subplot(rows,cols,p*2+4);
plot(trame2 + b);






figure 
plot(egaliseur([trame1 trame2], p, T))

moyenneur(egaliseur([trame1 trame2], p, T), numericSignalLength, nSamplesPerBit, 1.1);

teb = sum ( moyenneur(egaliseur([trame1 trame2], p, T), numericSignalLength, nSamplesPerBit, 1.1) - numericalSignal) /numericSignalLength

