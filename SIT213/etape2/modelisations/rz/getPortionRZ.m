function [portion] = getPortionRZ(bit, nSamplesPerBit, amplMin, amplMax)

portion1Length = floor(nSamplesPerBit/2);
portion2Length = nSamplesPerBit - portion1Length;

portion1 = ones(1,portion1Length)*((bit==0)*amplMin + (bit==1)*amplMax );
portion2 = zeros(1,portion2Length);

portion = [portion1 portion2];