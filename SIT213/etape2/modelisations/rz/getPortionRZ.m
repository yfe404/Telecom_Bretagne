function [portion] = getPortionRZ(bit, nSamplesPerBit, amplMin, amplMax)

portion1Length = floor(nSamplesPerBit/3);
portion3Length = portion1Length
portion2Length = nSamplesPerBit - portion1Length - portion3Length ;

portion1 = zeros(1,portion1Length);
portion2 = ones(1,portion2Length)*((bit==0)*amplMin + (bit==1)*amplMax );
portion3 = zeros(1,portion3Length);

portion = [portion1 portion2 portion3];