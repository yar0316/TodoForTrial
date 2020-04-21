
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
                "complete": false
            })
        },
        getAllTasks: function () {
            axios.get('/tasks')
                .then(response => this.tasks = response)
                .bind(this)
        },
    },
    created() {
        this.taskList = [{
            "title": "Task1",
            "complete": false,
            "editFlag": false
        },
        {
            "title": "Task2",
            "complete": false,
            "editFlag": false
        },
        {
            "title": "Task3",
            "complete": true,
            "editFlag": false
        }]
        console.log(this.taskList)
    }
});
