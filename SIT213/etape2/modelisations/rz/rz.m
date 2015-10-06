function [signalRZ] = rz(numericalSignal, nSamplesPerBit, numericSignalLength, amplMin, amplMax)

signalRZ = zeros(1,length(numericalSignal) * nSamplesPerBit); % Initialize NRZ signal with 0s.

for i=1:numericSignalLength
  signalRZ((i-1)*nSamplesPerBit+1:i*nSamplesPerBit) = getPortionRZ(numericalSignal(i), nSamplesPerBit, amplMin, amplMax);
end

