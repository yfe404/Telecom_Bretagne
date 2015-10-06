% Construction du vecteur de temps 
% La durée du signal est de 4 \mu secondes 
% pour 101 echantillons
duree_symbole = 4;
Ne = 101;
pas = duree_symbole*(1/(Ne-1));
t=0:pas:duree_symbole;

% Construction du symbole
s_signal = sin(2*pi*t);

%plot(t, s_signal);

% Construction du signal reçu 
% Ajout d'un délais, d'une atténuation et d'un bruit 
tr = 0:pas:40;
signal_recu = zeros(1 , length(tr));
OFFSETS = [15 25];
%%offset = 15; % instant debut reception symbole 
INDICES_INSERTIONS = floor(OFFSETS ./ pas);
%%indice_insertion = floor(offset / pas); % indice d'insertion du symbole 
for i=1:length(OFFSETS)
  signal_recu(INDICES_INSERTIONS(i):INDICES_INSERTIONS(i)+length(s_signal)-1) = s_signal;
end 

% Atténuation du signal en reception
facteur_att = 1;
signal_recu = signal_recu * facteur_att;

% Ajout du bruit
bruit = randn(1, length(signal_recu));
puiss_bruit = 0.4;
signal_recu = signal_recu + puiss_bruit * bruit;
%plot(tr, signal_recu);

% Réponse impulsionnelle = symbole inversé pour maximiser le SNR
rep_impul = fliplr(s_signal);

% Sortie du filtre
sortie = conv(signal_recu, rep_impul);
 plot(sortie);

% Recherche des indices où la corrélation est maximale
INDICES_CORREL_MAX = zeros(1, length(OFFSETS)); 
for i=1:length(OFFSETS)
  [mx, imx] = max(sortie);
  INDICES_CORREL_MAX(i) = imx;
  sortie(imx - floor(length(s_signal)):imx + floor(length(s_signal))) = 0; 
end
plot(sortie);

% Normalisation par rapport au pas utilisé 
%%instant_fin_reception = (imx * pas);
INSTANTS_FIN_RECEPTION = INDICES_CORREL_MAX .* pas;

% Test de la réussite de notre réception
for i=1:length(OFFSETS)
  if INSTANTS_FIN_RECEPTION(i) == (OFFSETS(i) + duree_symbole)
    disp("Succes");
  else
    disp("Erreur de détection");
  endif
end


