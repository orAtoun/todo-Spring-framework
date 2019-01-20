const Templates = {
    todo: function ( todoItem ) {
        const isChecked = todoItem.done ? 'checked' : ''

        return `
            <div class="row todo-item">
                <div class="col-md-12 mb-4">
                    <div class="card">
                        <div class="card-body">
                            <div class="row">
                                <div class="content col-md-11">
                                    <h4 class="text-left default-text py-1">${ todoItem.title }</h4>
                                    <em>${ todoItem.destination }</em>
                                    <p>${ todoItem.content }</p>
                                </div>
                                <div class="actions col-md-1 text-right">
                                    <div class="d-flex align-items-center flex-row mb-3" style="height: 100%">
                                        <div class="custom-control custom-checkbox">
                                            <input type="checkbox" value="${ todoItem.id }" class="custom-control-input" id="toggle-done-${ todoItem.id }" ${isChecked}>
                                            <label class="custom-control-label toggle-done" for="toggle-done-${ todoItem.id }"></label>
                                        <a style="font-size: 18px;" data-id="${ todoItem.id }" class="remove"><i class="fas fa-trash danger-text"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>`
    }
}

const Board = {
    service: BoardService,

    addNewTodo: function () {
        const todoForm = $( '#add-todo-form' )

        const todoItem = {
            title: todoForm.find( 'input[name=title]' ).val(),
            destination: todoForm.find( 'input[name=date]' ).val(),
            content: todoForm.find( 'textarea[name=content]' ).val()
        }

        this.service.createNewTodo( todoItem ).then( todo_id => {
            todoItem.id = todo_id
            const template = Templates.todo( todoItem )

            $( '#all-todos' ).prepend( template )
            this.clearFields()
        } )
    },

    clearFields: function () {
        const todoForm = $( '#add-todo-form' )

        todoForm.find( 'input[name=title]' ).val( '' )
        todoForm.find( 'input[name=date]' ).val( '' )
        todoForm.find( 'textarea[name=content]' ).val( '' )
    },

    removeTodo: function () {
        const id = $( this ).data( 'id' )

        Board.service.deleteTodo( id ).then( () => {
            $( this.closest( '.todo-item' ) ).fadeOut()
        } )
    },

    toggleDone: function () {
        const id = $( $( this ).siblings()[ 0 ] ).val()

        Board.service.toggleDone( id )
    },

    bindEvents: function () {
        $( '#save-todo' ).click( this.addNewTodo.bind( this ) )
        $( '#all-todos' ).on( 'click', '.todo-item .remove', this.removeTodo )
        $( '#all-todos' ).on( 'click', '.todo-item .toggle-done', this.toggleDone )
    },

    loadTodos: function () {
        this.service.getAllTodos().then( todos => {
            console.log(todos);
            for ( let todo of todos ) {
                let template = Templates.todo( todo )
                $( '#all-todos' ).prepend( template )
            }
        } )
    },

    init: function () {
        this.loadTodos()
        this.bindEvents()
    }
}

$( document ).ready( Board.init.bind( Board ) )
