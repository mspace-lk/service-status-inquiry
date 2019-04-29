function updateDocumentStatus(refNumber, newStatus) {
    $.ajax({
        url: "/service-status-inquiry/update/request/" + refNumber,
        type: 'POST',
        data: {
            status: newStatus
        },
        success: function() {
            window.location.reload();
        }
    });
}