const ReferenceApi = {

    deleteReferenceById: function (id) {
        $.ajax({
            url: '/api/v1/reference/delete/' + id,
            type: 'delete',
            success: function (response) {
                console.error(id);
                location.reload()
            },
            error: (err) => {
                alert(err);
            }
        })
        modal.modal('hide');
    }
}