function  [x] = moyennage (signalAnalogique, taille_ech, seuil)

x = zeros(1, length(signalAnalogique)/taille_ech);


for i=1:length(x)
  x(1,i) = mean(signalAnalogique((i-1)*taille_ech+1:i*taille_ech)) >= seuil;
end;
