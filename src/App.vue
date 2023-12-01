<template>
  <div class="bts">
    <button class="button-89" role="button" @click="addCircle()"><i class="fa-solid fa-circle"></i></button>
    <button class="button-89" role="button" @click="addEllipse()">ellipse</button>
    <button class="button-89" role="button" @click="addRectangle()"><i class="fa-solid fa-rectangle-list"></i></button>
    <button class="button-89" role="button" @click="addLine()"><i class="fa-solid fa-grip-lines"></i></button>
    <button class="button-89" role="button" @click="addSquare()"><i class="fa-solid fa-square"></i></button>
    <button class="button-89" role="button" @click="addTriangle()"><i class="fa-solid fa-caret-up"></i></button>
    <button class="button-89" role="button" @click="save()"><i class="fa-solid fa-floppy-disk"></i></button>
    <button class="button-89" role="button" @click="load()"><i class="fa-solid fa-floppy-disk"></i></button>
  </div>
  <div class="all">
    <div class="stage">
      <div class="clrs">
        <div class="colors">
          <div v-for="(color, index) in colorPalette" :key="index" @click="setColor(color)">
            <div :style="{ backgroundColor: color }" class="color-square"></div>
          </div>
        </div>
        <div>
          Selected Color: {{ selectedColor }}
        </div>
      </div>
      <v-stage :config="configKonva">
        <v-layer>
          <v-circle v-for="(circle, index) in shapes.circles" :key="circle.id" draggable="true" @dragstart="newInd(index)" :config="circle" @dragend="drageNew" @dblclick="showResizeForm(index,circle)" @click="changeColor(circle)"></v-circle>
          <v-rect v-for="(rect, index) in shapes.rectangles" :key="rect.id" draggable="true" @dragstart="newInd(index)" :config="rect" @dragend="drageNewR" @dblclick="showResizeForm(index,rect)" @click="changeColor(rect)"></v-rect>
          <v-line v-for="(line, index) in shapes.lines" :key="line.id" draggable="true" @dragstart="newInd(index)" :config="line" @dragend="drageNewL" @dblclick="showResizeForm(index,line)" @click="changeColor(line)"></v-line>
          <v-rect v-for="(sq, index) in shapes.squares" :key="sq.id" draggable="true" @dragstart="newInd(index)" :config="sq" @dragend="drageNewS" @dblclick="showResizeForm(index,sq)" @click="changeColor(sq)"></v-rect>
          <v-ellipse v-for="(ellipse, index) in shapes.ellipses" :key="ellipse.id" draggable="true" @dragstart="newInd(index)" :config="ellipse" @dragend="drageNewE" @dblclick="showResizeForm(index,ellipse)" @click="changeColor(ellipse)"></v-ellipse>
          <v-regular-polygon v-for="triangle in shapes.triangles" :key="triangle.id" :config="triangle"  draggable="true" @dragstart="newInd(triangle.index)" @dragend="drageNewT" @click="changeColor(triangle)"/>
        </v-layer >
      </v-stage>
    </div>
  </div>
  <div v-if="showForm">
    <label v-if="selectedShapeType==='rectangle'">
      Width:
      <input type="number" v-model="resizeForm.width" />
    </label>
    <label v-if="selectedShapeType==='rectangle'">
      Height:
      <input type="number" v-model="resizeForm.height" />
    </label>
    <label v-if="selectedShapeType==='square'">
      side length:
      <input type="number" v-model="resizeForm.width" />
    </label>
    <label v-if="selectedShapeType==='circle'">
      radius:
      <input type="number" v-model="resizeForm.width" />
    </label>
    <label v-if="selectedShapeType==='ellipse'">
      radiusX:
      <input type="number" v-model="resizeForm.width" />
    </label>
    <label v-if="selectedShapeType==='ellipse'">
      radiusY:
      <input type="number" v-model="resizeForm.height" />
    </label>
    <button @click="applyResize">Apply</button>
  </div>
</template>

<script>
// import { shapes } from 'konva/lib/Shape';
import { VRegularPolygon } from 'vue-konva';



export default {
  name: 'App',
  components: {
    VRegularPolygon
  },
  data() {
    return {
      colorPalette: [
        "#FF0000", "#FF7F00", "#FFFF00", "#7FFF00",
        "#00FF00", "#00FFFF", "#007FFF",
        "#0000FF", "#7F00FF", "#FF00FF",
        "#FFFFFF", "#C0C0C0", "#808080", "#000000"
      ],
      selectedColor: null,
      draggedShapeIndex: null,
      ind:null,
      initID:0,
      shapes: {
        rectangles:[],
        circles: [],
        lines: [],
        squares:[],
        ellipses:[],
        triangles:[]
        
      },
      showForm: false,
      selectedShapeIndex: null,
      selectedShapeType: null,
      resizeForm: {
        width: 0,
        height: 0,
      },
      shapeIdCounter: 1,
      configKonva: {
        width: 1400,
        height: 600
      },
    };
  },
  methods: {
    showResizeForm(index,shape) {
      this.selectedShapeIndex = index;
      this.showForm = true;
      this.selectedShapeType=shape.type;
    },
    changeColor(shape){
      if(this.selectedColor){
      if(shape.type==="rectangle"){
        this.shapes.rectangles[shape.index].fill=this.selectedColor.toLowerCase();
      }
      if(shape.type==="circle"){
        this.shapes.circles[shape.index].fill=this.selectedColor.toLowerCase();
      }
      if(shape.type==="ellipse"){
        this.shapes.ellipses[shape.index].fill=this.selectedColor.toLowerCase();
      }
      if(shape.type==="line"){
        this.shapes.lines[shape.index].fill=this.selectedColor.toLowerCase();
        
    }
    if(shape.type==="square"){
        this.shapes.squares[shape.index].fill=this.selectedColor.toLowerCase();
      }
    if(shape.type==="triangle"){
      this.shapes.triangles[shape.index].fill=this.selectedColor.toLowerCase();
    }
      }
      this.selectedColor=null;
    },
    applyResize() {
      if (this.selectedShapeIndex !== null) {
        if(this.selectedShapeType==="rectangle"){
        if (this.resizeForm.width) {
          this.shapes.rectangles[this.selectedShapeIndex].width = this.resizeForm.width;
        }
        if (this.resizeForm.height) {
          this.shapes.rectangles[this.selectedShapeIndex].height = this.resizeForm.height;
        }
        this.showForm = false;
      }
      if(this.selectedShapeType==="circle"){
        if (this.resizeForm.width) {
          this.shapes.circles[this.selectedShapeIndex].radius = this.resizeForm.width;
        }
        this.showForm = false;
      }
      if(this.selectedShapeType==="square"){
        if (this.resizeForm.width) {
          this.shapes.squares[this.selectedShapeIndex].width = this.resizeForm.width;
          this.shapes.squares[this.selectedShapeIndex].height = this.resizeForm.width;
        }
        this.showForm = false;
      }
      if(this.selectedShapeType==="ellipse"){
        if (this.resizeForm.width) {
          this.shapes.ellipses[this.selectedShapeIndex].radiusX = this.resizeForm.width;
          
        }
        if(this.resizeForm.height){
          this.shapes.ellipses[this.selectedShapeIndex].radiusY = this.resizeForm.height;
        }
        this.showForm = false;
      }
      this.resizeForm.width=0;
      this.resizeForm.height=0;
    }
    },
    newInd(index) {
    this.draggedShapeIndex = index;
  },
  addTriangle() {
    this.shapes.triangles.push({
      index: this.shapes.triangles.length,
      id: this.shapeIdCounter++,
      type:"triangle",
      x: 100,
      y: 100,
      sides:3,
      radius: 50,
      fill: "blue",
      stroke: "black",
      strokeWidth: 4,
  });
},
  addEllipse() {
    this.shapes.ellipses.push({
      index: this.shapes.ellipses.length,
      id: this.shapeIdCounter++,
      type:"ellipse",
      x: 100,
      y: 100,
      radiusX: 70,
      radiusY: 50,
      fill: "blue",
      stroke: "black",
      strokeWidth: 4,
  });
},

    addRectangle() {

      this.shapes.rectangles.push({
        index: this.shapes.rectangles.length,
        id: this.shapeIdCounter++,
        type:"rectangle",
        x: 100,
        y: 100,
        width:100,
        height:60,
        fill: "red",
        stroke: "black",
        strokeWidth: 4,
      });
      
    },
    addCircle() {
      this.shapes.circles.push({
        index: this.shapes.circles.length,
        id:this.initID++,
        type:"circle",
        x: 100,
        y: 100,
        radius: 70,
        fill: "red",
        stroke: "black",
        strokeWidth: 4,

      });
    },
    addLine() {
      this.shapes.lines.push({
        index: this.shapes.lines.length,
        type:"line",
        x: 100,
        y: 100,
        points: [300, 300, 400, 400],
        stroke: 'green',
        strokeWidth: 5,
      });
    },
    addSquare() {

      this.shapes.squares.push({
        index: this.shapes.squares.length,
        id: this.shapeIdCounter++,
        type:"square",
        x: 100,
        y: 100,
        width:100,
        height:100,
        fill: "pink",
        stroke: "black",
        strokeWidth: 4,
    });

},
    drageNew(e) {
    if (this.draggedShapeIndex !== null) {
      this.shapes.circles[this.draggedShapeIndex].x = e.target.attrs.x;
      this.shapes.circles[this.draggedShapeIndex].y = e.target.attrs.y;
      console.log(this.shapes.circles);
    }
  },
  drageNewR(e) {
    if (this.draggedShapeIndex !== null) {
      this.shapes.rectangles[this.draggedShapeIndex].x = e.target.attrs.x;
      this.shapes.rectangles[this.draggedShapeIndex].y = e.target.attrs.y;
      console.log(this.shapes.rectangles);
    }
  },
  drageNewL(e) {
    if (this.draggedShapeIndex !== null) {
      this.shapes.lines[this.draggedShapeIndex].x = e.target.attrs.x;
      this.shapes.lines[this.draggedShapeIndex].y = e.target.attrs.y;
      console.log(this.shapes.lines);
    }
  },
  drageNewS(e) {
    if (this.draggedShapeIndex !== null) {
      this.shapes.squares[this.draggedShapeIndex].x = e.target.attrs.x;
      this.shapes.squares[this.draggedShapeIndex].y = e.target.attrs.y;
      console.log(JSON.stringify(this.shapes.squares));
    }
  },
  drageNewE(e) {
  if (this.draggedShapeIndex !== null) {
    this.shapes.ellipses[this.draggedShapeIndex].x = e.target.attrs.x;
    this.shapes.ellipses[this.draggedShapeIndex].y = e.target.attrs.y;
    console.log(this.shapes.ellipses);
  }
},
drageNewT(e) {
  if (this.draggedShapeIndex !== null) {
    this.shapes.triangles[this.draggedShapeIndex].x = e.target.attrs.x;
    this.shapes.triangles[this.draggedShapeIndex].y = e.target.attrs.y;
    console.log(this.shapes.triangles);
  }
},
save(){
    fetch('http://localhost:8080/circles', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(this.shapes.circles)
    }).catch(err=>{
      console.log(err);
    })
    fetch('http://localhost:8080/squares', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(this.shapes.squares)
    }).catch(err=>{
      console.log(err);
    })
    fetch('http://localhost:8080/rectangles', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(this.shapes.rectangles)
    }).catch(err=>{
      console.log(err);
    })
    fetch('http://localhost:8080/writeJson', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
    })
  },
   load(){
     fetch('http://localhost:8080/readJson', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
    }).then(res=>res.json()).then(data=>{
      this.shapes=data;
      console.log(this.shapes);
    }).catch(err=>{
      console.log(err);
    })
  },
  async write(){
    await fetch('http://localhost:8080/writeJson', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
    })
  },
  setColor(color) {
      this.selectedColor =this.hexToColorNameWithComparison(color);
    },
     hexToColorNameWithComparison(hexCode)  {
      let colorMappings = {
        '#FF0000': 'Red',
        '#FF7F00': 'Orange',
        '#FFFF00': 'Yellow',
        '#7FFF00': 'Chartreuse',
        '#00FF00': 'Lime',
        '#00FF7F': 'Spring Green',
        '#00FFFF': 'Cyan',
        '#007FFF': 'Azure',
        '#0000FF': 'Blue',
        '#7F00FF': 'Violet',
        '#FF00FF': 'Magenta',
        '#FF007F': 'Rose',
        '#FFFFFF': 'White',
        '#C0C0C0': 'Silver',
        '#808080': 'Gray',
        '#000000': 'Black',
      };

      let colorName = colorMappings[hexCode.toUpperCase()];
      return colorName ? colorName : 'Unknown';
}
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 0px;
  
}
body{
  background-image: url("vecteezy_simple-childish-scribble-backdrop-colorful-doodle-art_8362482.jpg");
}
.all {
  display: flex;
  justify-content: center;
  align-items: center;
}
.stage {
  border: #2c3e50 1px solid;
  display: flex;
  flex-flow: column wrap;
  align-items: center;
}

.stage{
  border: #2c3e50 1px solid;
  display: flex;
  flex-flow: column wrap;
  justify-content: center;
  align-items:center ;
}
button{
  width: 100px;
  height: 50px;
}
.bts{
  width: 100%;
  display: flex;
  flex-flow: row wrap;
  justify-content: space-evenly;
}
.color-square {
  width: 10px;
  height: 10px;
  border: 1px solid #000;
  cursor: pointer;
  margin: 5px;
}
.clrs{
  display: flex;
  flex-flow: column wrap;
  border: #000 1px solid;
}
.stage{
  background-color: white;
}

.button-89 {
  --b: 3px;  
  --s: .45em;
  --color: #373B44;
  
  padding: calc(.5em + var(--s)) calc(.9em + var(--s));
  color: var(--color);
  --_p: var(--s);
  background:
    conic-gradient(from 90deg at var(--b) var(--b),#0000 90deg,var(--color) 0)
    var(--_p) var(--_p)/calc(100% - 2*var(--_p)) calc(100% - 2*var(--_p)); /* Modified this line */
  transition: .3s linear, color 0s, background-color 0s;
  outline: var(--b) solid #0000;
  outline-offset: .6em;
  font-size: 16px;

  border: 0;

  user-select: none;
  -webkit-user-select: none;
  touch-action: manipulation;
}


.button-89:hover,
.button-89:focus-visible{
  --_p: 0px;
  outline-color: var(--color);
  outline-offset: .05em;
}

.button-89:active {
  background: var(--color);
  color: #fff;
}
.colors{
  display: flex;
}
</style>
