% Le transmetteur inclu le canal de transmission et les composants des extrémités.

% Rappel : SNR : Puissance moyenne du signal / Puissance moyenne du bruit (linéaire) 

x = 0:0.1:100000;
pure = 2.*x+5;

a1 = rand(1,length(pure));
a2 = rand(1,length(pure));

% Ecart-type
sigma = 5;

%% Puissance = variance
P = sigma^2;
b = sigma .* sqrt(-2.*log(1-a1)) .* cos(2*pi.*a2);

%plot(pure + b);
histfit(b, 100);
%plot(b)

