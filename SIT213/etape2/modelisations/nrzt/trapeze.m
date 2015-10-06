function [s] = trapeze(nSamples, amplMin, amplMax)

s1Length = floor((1/3) * nSamples);
s3Length = s1Length;
s2Length = nSamples - s1Length - s3Length;


s1 = zeros(1,s1Length);
a1 = ((amplMax-amplMin)/s1Length)/(1-(1/s1Length))
b1 = amplMin 

x = 0;

for i=1:s1Length
  s1(i)=a1*x + b1;
  x+=1;
end;

s3 = zeros(1,s3Length);
a3 = (amplMin-amplMax)/(nSamples-(s1Length+s2Length))
b3 = amplMax-a3*(s1Length+s2Length)


x = s1Length+s2Length+1;
for i=1:s3Length
   s3(i)=a3*x+b3;
   x+=1;
end

s2 = amplMax*ones(1, s2Length);

s = [s1 s2 s3];


