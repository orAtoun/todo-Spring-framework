jQuery.each( [ 'put', 'delete', 'patch' ], function ( i, method ) {
    jQuery[ method ] = function ( url, data, callback, type ) {
        if ( jQuery.isFunction( data ) ) {
            type = type || callback
            callback = data
            data = undefined
        }

        return jQuery.ajax( {
            url: url,
            contentType: 'application/json',
            type: method,
            dataType: type,
            data: data,
            success: callback
        } )
    }
} )

const BoardService = {
    ENDPOINT: 'http://localhost:8080/api',

    createNewTodo: function ( todoItem ) {
        return $.ajax( {
            type: 'POST',
            contentType: 'application/json',
            url: `${ this.ENDPOINT }/todo`,
            data: JSON.stringify( todoItem ),
            dataType: 'json'
        } )
    },

    getAllTodos: function () {
        return $.get( `${ this.ENDPOINT }/todo` )
    },

    deleteTodo: function ( id ) {
        return $.delete( `${ this.ENDPOINT }/todo/${ id }` )
    },

    toggleDone: function ( id ) {
        return $.put( `${ this.ENDPOINT }/todo/done/${ id }` )
    }
}
