
var app = new Vue({
    el: "#app",
    data: {
        input: "",
        showOnlyIncomplete: false,
        taskList:[]
    },
    methods: {
        addNewItem: function () {
            this.taskList.push({
                "title": this.input,
                "progressFlag": false
            })
        },
        getAllTasks: function () {
            axios.get('tasks')
                .then(response => this.taskList = response.data,
                    error => console.log(error))
                .bind(this)
        },
        deleteTask: function (itemName, index) {
            if(confirm(`${itemName}を削除しますか？`)){
            // axios.delete('/delete', {params: this.taskList[index]})
                this.taskList = this.taskList.filter(n => n !== this.taskList[index])
            }
        },
        switchEdit: function (index) {
            this.taskList[index].editFlag = !this.taskList[index].editFlag
            bind(this)
            console.log(this.taskList[index].editFlag)
            // if (this.taskList[index].editFlag === false){this.confirmEdit(index)}
        },
        confirmEdit: function (index) {
            axios.post('http://localhost:8090/edit', this.taskList[index])
                .then(response => console.log(response.data))
                .bind(this)
        }
    },
    created() {
        this.getAllTasks()
        console.log(this.taskList)
    }
});
