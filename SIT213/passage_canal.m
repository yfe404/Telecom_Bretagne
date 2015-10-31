function [y] = passage_canal(p, A, T, signal_entree)


y = zeros(1, length(signal_entree) + max(T));

% Trajet direct

for i = 1:length(signal_entree)
  y(i) = signal_entree(i);
end


% Multi trajet
for i = 1:p
for j = 1 : length(signal_entree)
  y(j+T(i)) += A(p) * signal_entree(j);
end
end
