const app =  Vue.createApp({
   
    data() {
       return {
           textareaVal: '34,21,32,41,25,14,42,43,14,31,54,45,52,42,23,33,15,51,31,35,21,52,33,13,23',
           responseValue: '',
           treasureFound: '',
           errorMsgs: '',
        }
    },
    methods: {
        postTreasureMap() {
            this.responseValue =''
            this.treasureFound =''
            this. errorMsgs = ''
            const value = this.textareaVal.split(',');
            const treemap = this.createRequestJson(value);

            const requestOptions = {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(treemap)
              };
              fetch("http://localhost:8080/treasurehunt/map", requestOptions)
                .then(response => response.json())
                .then(data => this.createResponse(data));
                        
        },
        createRequestJson(value){
            let treemap = {nodes:[]};            
            let row =1;
            let col =1;
            for(i=0;i<value.length;i++){
                var node = {}
                treemap.nodes.push(node)
                node.row = row;
                node.column = col;
                node.clueValue = value[i]
                col++
                if(col%6===0){
                    row++
                    col=1 
                }
                
            }
            // console.log(treemap)
            return treemap            
        },
        createResponse(response){
            console.log(response)
            const {nodesVisited, treasureFound, treasureNode}  =  response
            // const {treasureFound} = response
            if(response.status){
              if(response.status==='400')
                 this.errorMsgs = `Invalid payload: ${response.errorMessages[0].message}`
            }
            console.log(nodesVisited)
            console.log(treasureFound)
            if(treasureFound){
                this.responseValue = "Indices traversed: "
                nodesVisited.forEach(element => {
                    this.responseValue += ", " + element.row+element.column
                });
                this.treasureFound +=`Treasure found at node: ${treasureNode.row}${treasureNode.column}` // + treasureNode.row+ ""+treasureNode.column
            }
        }
    }
})

app.mount('#app')