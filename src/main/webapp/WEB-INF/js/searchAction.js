function searching(request) {
    $.ajax({
        type: "GET",
        url: "search?request=" + request,
        timeout: 100000,
        data: {offset: this.offset},
        success: function (data) {
            var row = "";
            data.forEach(function (item) {
                row += '<tr>' +
                    '<td>' + item.label + '</td>\n' +
                    '<td>' + item.count + '</td>\n' +
                    '<td>' + item.lastUpdate + '</td>\n' +
                    '<td>' + item.pharmacyName + '</td>\n' +
                    '<td>' + item.address + '</td>\n' +
                    '<td>' + item.contactNumber + '</td>' +
                    '</tr>';
            });
            $('.medicaments-search-table-body').html(row);
            $('#searched-medicaments-count').text(data.length);
        }
    });
}
