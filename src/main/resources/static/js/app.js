
var app = new Vue({
    el: "#app",
    data: {
        input: "",
        showOnlyIncomplete: false,
        taskList:[]
    },
    methods: {
        addNewItem: function () {
            let index = this.taskList.length
            const newItem = {
                "title": this.input
            }
            console.log('addNewItem called.')
            axios.post('insertUpdate', newItem)
                .then(response => {
                    console.log(response.data)
                    this.taskList.push(response.data)
                })
                .bind(this)
        },
        getAllTasks: function () {
            axios.get('tasks')
                .then(response => this.taskList = response.data,
                    error => console.log(error))
                .bind(this)
            console.log(this.taskList)
        },
        deleteTask: function (itemName, index) {
            console.log('deleteTask called.')
            if(confirm(`${itemName}を削除しますか？`)){
                let deleteTaskId = this.taskList[index].id
                this.taskList = this.taskList.filter(n => n !== this.taskList[index])
                axios.delete(`delete/${deleteTaskId}`)
                    .then(response => console.log(response.data))
                    .bind(this)
            }
        },
        switchEdit: function (index) {
            let clicked = this.taskList[index]
            console.log('switchEdit called.')
            console.log(clicked)
            clicked.editFlag = !clicked.editFlag
            this.taskList.splice(index, 1, clicked)
            if (clicked.editFlag === false){this.confirmEdit(this.taskList[index])}
        },
        switchProgress: function(index) {
            let clicked = this.taskList[index]
            console.log("switchProgress called.")
            console.log(clicked)
            this.confirmEdit(clicked)
        },
        confirmEdit: function (task) {
            console.log('confirmEdit called.')
            console.log(task)
            axios.post('/insertUpdate', task)
                .then(response => console.log(response.data))
                .bind(this)
        }
    },
    created() {
        this.getAllTasks()
    }
});
