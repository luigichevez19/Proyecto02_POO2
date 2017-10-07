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
            consTipo([{name : 'codiLote', value : row.id.trim()}]);
        });
        return false;
    };
    
    $('#modaFormLote').on('show.bs.modal', function() {
        INIT_OBJE_MODA_TIPO();
    });
    
    INIT_OBJE_TIPO();
});

function INIT_OBJE_TIPO()
{
    $("#tablLote").initBootTable();
}

function INIT_OBJE_MODA_TIPO()
{
    $("#formTest\\:btonElim").confirmation({container: '#formTest'});
}

