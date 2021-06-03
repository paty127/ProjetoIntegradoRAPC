$('.secretaria').click(function(){
    $('.menuLateral ul .itensSecretaria').toggleClass('mostra');
    $('.menuLateral ul .seta1').toggleClass('gira');
});
    

$('.professor').click(function(){
    $('.menuLateral ul .itensProfessor').toggleClass('mostra');
     $('.menuLateral ul .seta2').toggleClass('gira');
    
});
$('.diretor').click(function(){
    $('.menuLateral ul .itensDiretor').toggleClass('mostra');
     $('.menuLateral ul .seta3').toggleClass('gira');
    
});
$('.btnAbre').click(function(){
    $('.menuLateral').toggleClass('mostra');
    
});
$('.btnFecha').click(function(){
    $('.menuLateral').toggleClass('mostra');
    
});
