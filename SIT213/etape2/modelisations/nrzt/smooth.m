function [x] = smooth(ech)

N = length(ech);
HN1 = floor(N/2);
HN2 = N - HN1;

x = zeros(1, N);

for i = 2 : HN1 -1
 x(i) = 0.25*(ech(i-1)+2*ech(i)+ech(i+1)); 
end;

for i = 1 : HN2 
x(N-i) = (1/5)*(2*ech(N-i-1) + ech(N-i) + 2*ech(N-i+1));
end;