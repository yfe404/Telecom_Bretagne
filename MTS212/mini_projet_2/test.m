load('series_observees/suite2_4096.mat');

[g, u, e] = kolmogorov_smirnov(suite2);
n = 4096;
alpha = 0.05;

seuilH_0 = sqrt((-1/(2*n)) * log(alpha/2));

if g <= seuilH_0 
  disp("Gaussian");
endif;
if u <= seuilH_0 
  disp("Uniform");
endif;
if e <= seuilH_0 
  disp("Exponential");
endif;

