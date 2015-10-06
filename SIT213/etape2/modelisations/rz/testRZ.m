nSamplesPerBit = 10; % number of samples per bit 
numericSignalLength = 10; % length of the numerical signal 

amplMin = -1; % min ampl of the RZ signal 
amplMax = 1; % max ampl of the RZ signal 

numericalSignal = floor(mod((randn(1,numericSignalLength)), 2)) % Generate a random numerical signal

RZSignal = rz(numericalSignal, nSamplesPerBit, numericSignalLength, amplMin, amplMax);

plot(RZSignal);