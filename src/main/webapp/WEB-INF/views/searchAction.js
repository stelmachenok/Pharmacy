$('#searchButton').onclick(function () {
    $.ajax({
        url: "search",
        data: {request: 'asd'}
    })
        .done(function (data) {
            var s;
            data.forEach(function (item) {
                var row = '<td>${searchObj.brandName}</td>\n' +
                    '        <td>${searchObj.phone}</td>\n' +
                    '        <td>${searchObj.count}</td>'
                    $('.medicaments-search-table-body').append(row);
            });

            /*$("div.demo-container")
                .html("<p>All new content. <em>You bet!</em></p>");*/
        });
})

function fgh(some_data) {

}
