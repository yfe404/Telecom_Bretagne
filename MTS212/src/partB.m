load('signal1.mat');
load('signal2.mat');
load('signal3.mat');
load('symbole.mat');

pas = 0.04;
t = 0:pas:40;
t2 = 0:pas:4;

c = 300000000;
Fe = 500000;
Te = 1 / Fe;

% Position des antennes (km)
P = [0 0; 40 0; 0 40];


%% Réponse impulsionnelle
rep_impuls = fliplr(s_signal);

% Matrice des signaux reçus par les antennes 1, 2 et 3
signaux = [sig1; sig2; sig3];

% Matrice des signaux filtrés 
signaux_filtres = conv2(signaux, rep_impuls);

% Recherche des corrélations maximales
% MX est la matrice contenant les maximums, IMX contient les indices 
% de ces maximums
[MX, IMX] = max(signaux_filtres, [], 2);

% Normalisation par rapport au pas utilisé 
% Passe de l'indice à la valeur temporelle
T = IMX * Te;

% Normalisation par rapport au plis petit temps d'arrivé 
% Permet d'avoir les temps d'arrivés des signaux relativement à 
% l'antenne qui reçois le signal en premier
I = T - min(T);
I

% Passage du temps d'arrivée relatif à la distance relative (en km)
D = I * (c / 1000);
D

syst = @(x)[(x(1) - P(1,1))^2 + (x(2) - P(1,2))^2 - (x(3) + D(1,:))^2;
            (x(1) - P(2,1))^2 + (x(2) - P(2,2))^2 - (x(3) + D(2,:))^2;
            (x(1) - P(3,1))^2 + (x(2) - P(3,2))^2 - (x(3) + D(3,:))^2];

x0 = [0, 0, 0];
x = fsolve(syst, x0)



% 4T = 101
% T = (101 / 4)
% f = 1/T = 1 / (101/4)

