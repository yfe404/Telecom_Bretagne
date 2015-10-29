function [s] = moyenneur(signal_recu, nbEch, nbBitPerEch, seuil);

s = zeros(1, nbEch );

for i = 1:nbEch
  sum = 0;
  
  
  for j = 1 : nbBitPerEch
    sum += signal_recu(1, (i-1)*nbBitPerEch + j);
  end
  
  s(i) = (sum/nbBitPerEch) >= seuil;
  
end

