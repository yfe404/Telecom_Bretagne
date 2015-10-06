t = 0:0.1:5;
y = zeros(1, length(t));

for i=1:length(t)
y(i) = detection(t(1,i));
end;
plot(t, y);