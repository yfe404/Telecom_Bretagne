% appel [ MuEst, SigmaEst] = estimateurGaussien( data )
% dtermine les estimes des paramtres\mu et \sigma d'une loi
% gaussienne
% les termes " complter" sont  remplacer par la bonne formule matlab.
function [ MuEst, SigmaEst] = estimateurGaussien( data )

    N = size(data,1);

    % Estimation de l'espérance
    MuEst =  mean(data); 
    
    % Estimation de la variance
    SigmaEst =  var(data) ;
   
end