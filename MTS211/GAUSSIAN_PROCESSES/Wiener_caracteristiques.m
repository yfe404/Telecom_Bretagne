% Définition de l'échantillonnage temporel de la trajectoire
t = 0:0.01:10;

% Coefficient de diffusion du processus
sigma = 50;

% Nombre de processus à simuler pour le calcul expérimental de l'espérance et de la variance
nbSim = 50;

% Matrice des processus simulés
A = zeros(nbSim, length(t));

% Simulations de "nbSim" processus de Wiener
for i=1:nbSim
A(i,:) = Wiener(t, sigma);
end

% f contient les valeurs des espérances pour chaque instant
f = zeros(length(t));
% g contient les valeurs des variances pour chaque instant
f = zeros(length(t));

for i=1:length(t)
f(i) = mean(A(:,i));
g(i) = var(A(:,i));
end;


figure;
plot(t, f, 'r');
hold on;
plot(t, g, 'b');

