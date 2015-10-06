function [signalNRZ] = nrz(numericalSignal, nSamplesPerBit, numericSignalLength, amplMin, amplMax)

signalNRZ = zeros(1,length(numericalSignal) * nSamplesPerBit); % Initialize NRZ signal with 0s.

for i=1:numericSignalLength
  COND = (numericalSignal(i) == 0); % Boolean, 1 if current bit equals 0, 1 else
  % Add "nSamplesPerBit" samples to the NRZ analogic signal times "amplMin" if the current bit equals 0 or times "amplMax" else.
  signalNRZ((i-1)*nSamplesPerBit+1:i*nSamplesPerBit) = ((COND).*(ones(1,nSamplesPerBit)*amplMin)) + ((~COND).*(ones(1,nSamplesPerBit)*amplMax));
end

