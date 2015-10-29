function [s, r] = multipath(signal_origine, tau, taille_trame)

s = zeros(1, length(signal_origine));
r = zeros(1, taille_trame);

for i = 1:length(signal_origine)
 if i - tau >= 1
    s(i) = signal_origine(i - tau);
endif
end


for i = 1:taille_trame
  if i <= tau
    r(i) = signal_origine(length(signal_origine) - tau+i);
   endif 
end

 

    