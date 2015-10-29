function [s] = egaliseur(signal_recu, p, T);

s = zeros(1, length(signal_recu));

for i=1 : p 
 for j = 1 : length(signal_recu) - T(i)
  s(j) =  signal_recu(j) + signal_recu(j+T(i)) ;
 end
end