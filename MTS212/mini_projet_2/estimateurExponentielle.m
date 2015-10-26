% appel Lambda = estimateurExponentielle( data)
% estimateur du paramtre \lambda d'une loi exponentielle
function Lambda = estimateurExponentielle( data)
    N = length(data);
    
    Lambda = N / sum(data);
end