function [g, u, e] = kolmogorov_smirnov  (data)

[mu, sigma] = estimateurGaussien(data);
lambda = estimateurExponentielle(data);
[mn, mx ] = estimateurUniforme(data);

N = length(data);
x = linspace(mn, mx, N);

Fg = CDFGauss(x, mu, sigma);
Fe = CDFExponential(x, lambda);
Fu = CDFUniform(x, mn, mx);

y = cumsum(hist(data, length(x)))./N;

g = max(abs(Fg - y));
u = max(abs(Fu - y));
e = max(abs(Fe - y));


figure
subplot(3,2,1);
plot(x,Fg, 'LineWidth', 2, 'r');
hold on;
plot(x, y, 'LineWidth', 2, 'b');
title('Subplot 1: Loi Normale')

subplot(3,2,2);
plot(x, abs(Fg - y));
hold on;
err = abs(Fg - y);
line(get(gca,'Xlim'), [max(err) max(err)],'color', 'red');
xlabel('$F_0 - \hat{F}$' ,'interpreter','latex');

subplot(3,2,3);
plot(x,Fe, 'LineWidth', 2, 'r');
hold on;
plot(x, y, 'LineWidth', 2, 'b');
title('Subplot 2: Loi Exponentielle')

subplot(3,2,4);
plot(x, abs(Fe-y));
hold on;
err = abs(Fe - y);
line(get(gca,'Xlim'), [max(err) max(err)],'color', 'red') ;

subplot(3,2,5);
plot(x,Fu, 'LineWidth', 2, 'r');
hold on;
plot(x, y, 'LineWidth', 2, 'b');
title('Subplot 3: Loi Uniforme')

subplot(3,2,6);
plot(x, abs(Fu - y));
hold on;
err = abs(Fu - y);
line(get(gca,'Xlim'), [max(err) max(err)],'color', 'red'); 11