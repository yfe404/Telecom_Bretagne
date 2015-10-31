nSamplesPerBit = 30; % number of samples per bit 
numericSignalLength = 10; % length of the numerical signal 
amplMin = 0; % min ampl of the NRZ signal 
amplMax = 1.2; % max ampl of the NRZ signal 


%%%% TESTS %%%%

% Caractéristiques du canal 
p = 1; % Nombre de trajets multiples 
A = [0.9 .2 0.1 0.8 0.9]; % Amplitudes des trajets multiples
T = [105 125 99 5 18]; % décallages des trajets multiples en nombre d'échantillons


% Création du filtre de reception (apprentissage)

sequ_apprentissage_num = [1 0 1 0 1 0 1 0 1 0];
sequ_apprentissage_ana = nrzt(sequ_apprentissage_num, nSamplesPerBit, numericSignalLength, 0, 1);

sequ_apprentissage_post_canal = passage_canal(p, A, T, sequ_apprentissage_ana);
fft_sequ_apprentissage_post_canal = fft(sequ_apprentissage_post_canal);
fft_sequ_apprentissage_ana = [fft(sequ_apprentissage_ana) zeros(1, max(T))];

%% Suppressions des zeros dans la fft (pose des problemes de NAN)
for i=1 : length(fft_sequ_apprentissage_post_canal)
if fft_sequ_apprentissage_post_canal(i) == 0
fft_sequ_apprentissage_post_canal(i) = 1;
endif;
end

filtre_reception = fft_sequ_apprentissage_ana ./ fft_sequ_apprentissage_post_canal;

figure
subplot(1,2,1);
plot(sequ_apprentissage_ana)
subplot(1,2,2);
plot(abs(ifft(fft_sequ_apprentissage_post_canal .* filtre_reception)))


% Test du filtre après apprentissage sur une sequence aléatoire

sequ_aleatoire_num = floor(mod((randn(1,numericSignalLength)), 2))
sequ_aleatoire_ana = nrzt(sequ_aleatoire_num, nSamplesPerBit, numericSignalLength, 0, 1);
sequ_aleatoire_post_canal = passage_canal(p, A, T, sequ_aleatoire_ana);
fft_sequ_aleatoire_post_canal = fft(sequ_aleatoire_post_canal);


figure
subplot(1,2,1);
plot(sequ_aleatoire_ana)
subplot(1,2,2);
plot(abs(ifft(fft_sequ_aleatoire_post_canal .* filtre_reception)))

%% Calcul du TEB
seuil = 0;
sequ_aleatoire_num_reconstruite = moyenneur(ifft(fft_sequ_aleatoire_post_canal .* filtre_reception), numericSignalLength, nSamplesPerBit, seuil)
teb = abs(sum ( sequ_aleatoire_num_reconstruite - sequ_aleatoire_num) /numericSignalLength)


%%%% END TESTS %%%%

