function [signalNRZ] = nrzt(numericalSignal, nSamplesPerBit, numericSignalLength, amplMin, amplMax)

signalNRZ = zeros(1,length(numericalSignal) * nSamplesPerBit); % Initialize NRZ signal with 0s.

x=0
for i=1:numericSignalLength
  COND = (numericalSignal(i) == 0); % Boolean, 1 if current bit equals 0, 1 else
  % Add "nSamplesPerBit" samples to the NRZ analogic signal times "amplMin" if the current bit equals 0 or times "amplMax" else.
  signalNRZ((i-1)*nSamplesPerBit+1:i*nSamplesPerBit) = ((COND).*(ones(1,nSamplesPerBit)*amplMin)) + ((~COND).*(trapeze(nSamplesPerBit, amplMin, amplMax)));
end

