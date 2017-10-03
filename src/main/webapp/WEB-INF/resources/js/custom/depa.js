/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    $.fn.initBootTable = function() {
        $(this).bootstrapTable('destroy');
        $(this).bootstrapTable().
        unbind('check.bs.table').on('check.bs.table', function (e, row) {
            consTipo([{name : 'codiDepa', value : row.id.trim()}]);
        });
        return false;
    };
    
    $('#modaFormDepa').on('show.bs.modal', function() {
        INIT_OBJE_MODA_TIPO();
    });
    
    INIT_OBJE_TIPO();
});

function INIT_OBJE_TIPO()
{
    $("#tablDepa").initBootTable();
}

function INIT_OBJE_MODA_TIPO()
{
    $("#formTest\\:btonElim").confirmation({container: '#formTest'});
}

