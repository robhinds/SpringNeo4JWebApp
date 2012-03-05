(function(){

  Renderer = function(canvas){
    canvas = $(canvas).get(0);
    var ctx = canvas.getContext("2d");
    var particleSystem = null;
    
    function drawEllipse(ctx, x, y, w, h, colour, alpha) {
		var kappa = .5522848;
		ox = (w / 2) * kappa, // control point offset horizontal
		oy = (h / 2) * kappa, // control point offset vertical
		xe = x + w,           // x-end
		ye = y + h,           // y-end
		xm = x + w / 2,       // x-middle
		ym = y + h / 2;       // y-middle
		
		ctx.beginPath();
		ctx.moveTo(x, ym);
		ctx.bezierCurveTo(x, ym - oy, xm - ox, y, xm, y);
		ctx.bezierCurveTo(xm + ox, y, xe, ym - oy, xe, ym);
		ctx.bezierCurveTo(xe, ym + oy, xm + ox, ye, xm, ye);
		ctx.bezierCurveTo(xm - ox, ye, x, ym + oy, x, ym);
		ctx.closePath();
		ctx.stroke();
		if (alpha==null || alpha!=0){
			ctx.fillStyle = colour;
			ctx.fill();
		}
    };
 

    var that = {
      init:function(system){
        particleSystem = system;
        particleSystem.screen({padding:[100, 60, 60, 60], // leave some space at the bottom for the param sliders
                              step:.02}); // have the ‘camera’ zoom somewhat slowly as the graph unfolds 
       $(window).resize(that.resize);
       that.resize();
      
       that.initMouseHandling();
      },
      redraw:function(){
        if (particleSystem===null) return

        ctx.clearRect(0,0, canvas.width, canvas.height);
        ctx.strokeStyle = "#d3d3d3";
        ctx.lineWidth = 1;
        ctx.beginPath();
        particleSystem.eachEdge(function(edge, pt1, pt2){
          var weight = null; // Math.max(1,edge.data.border/100)
          var color = null; // edge.data.color
          if (!color || (""+color).match(/^[ \t]*$/)) color = null;

          if (color!==undefined || weight!==undefined){
            ctx.save() ;
            ctx.beginPath();

            if (!isNaN(weight)) ctx.lineWidth = weight
            
            // if (color) ctx.strokeStyle = color
            ctx.fillStyle = null;
            
            ctx.moveTo(pt1.x, pt1.y);
            ctx.lineTo(pt2.x, pt2.y);
            ctx.stroke();
            ctx.restore();
          }else{
            // draw a line from pt1 to pt2
            ctx.moveTo(pt1.x, pt1.y);
            ctx.lineTo(pt2.x, pt2.y);
          }
        });
        ctx.stroke();

        particleSystem.eachNode(function(node, pt){
          var w = ctx.measureText(node.data.label||"").width + 8;
          var label = node.data.label;
          if (!(label||"").match(/^[ \t]*$/)){
            pt.x = Math.floor(pt.x);
            pt.y = Math.floor(pt.y);
          }else{
            label = null;
          }
          
          // clear any edges below the text label
          ctx.clearRect(pt.x-w/2, pt.y-7, w,14);
          
          
          //drawcircle
          if (node.data.shape=='circle'){
        	  drawEllipse(ctx, pt.x-w/2, pt.y-w/2, w, w, node.data.color, node.data.alpha);
          }
          else{
        	  ctx.fillStyle = node.data.color;
              ctx.fillRect(pt.x-w/2, pt.y-7, w,18);
              ctx.fill();  
          }
          
          
          // draw the text
          if (label){
            ctx.font = "bold 11px Arial";
            ctx.textAlign = "center";
            ctx.fillStyle = "white";
            ctx.fillText(label||"", pt.x, pt.y+4);
          }
        });
      },
      
      resize:function(){
        var w = .75 * $(window).width(),
            h = .75 * $(window).height();
        canvas.width = w; canvas.height = h // resize the canvas element to fill the screen
        particleSystem.screenSize(w,h) // inform the system so it can map coords for us
        that.redraw()
      },

    	initMouseHandling:function(){
        // no-nonsense drag and drop (thanks springy.js)
      	selected = null;
      	nearest = null;
      	var dragged = null;
        var oldmass = 1

        $(canvas).mousedown(function(e){
      		var pos = $(this).offset();
      		var p = {x:e.pageX-pos.left, y:e.pageY-pos.top}
      		selected = nearest = dragged = particleSystem.nearest(p);

      		if (selected.node !== null){
            // dragged.node.tempMass = 10000
      			dragged.node.fixed = true;
      		}
      		return false;
      	});

      	$(canvas).mousemove(function(e){
          var old_nearest = nearest && nearest.node._id;
      		var pos = $(this).offset();
      		var s = {x:e.pageX-pos.left, y:e.pageY-pos.top};

      		nearest = particleSystem.nearest(s);
          if (!nearest) return

      		if (dragged !== null && dragged.node !== null){
      			var p = particleSystem.fromScreen(s);
      			dragged.node.p = {x:p.x, y:p.y};
            // dragged.tempMass = 10000
      		}

          return false;
      	});

      	$(window).bind('mouseup',function(e){
          if (dragged===null || dragged.node===undefined) return
          dragged.node.fixed = false;
          dragged.node.tempMass = 100;
      		dragged = null;
      		selected = null;
      		return false;
      	});
      	      
      },
            
    };
  
    return that;
  };
  
  var Maps = function(elt){
    var sys = arbor.ParticleSystem(4000, 500, 0.5, 55);
    sys.renderer = Renderer("#viewport"); // our newly created renderer will have its .init() method called shortly by sys...
    
    var dom = $(elt);   
    var _links = dom.find('ul');

    
    var that = {
      init:function(){
          $.getJSON("data/loadMovieData.json",function(data){
              // load the raw data into the particle system as is (since it's already formatted correctly for .merge)
              var nodes = data.nodes;
              $.each(nodes, function(name, info){
                  info.label=name.replace(/(people's )?republic of /i,'').replace(/ and /g,' & ')
              });
              
              sys.merge({nodes:nodes, edges:data.edges});
              sys.parameters({stiffness:900, repulsion:15000, gravity:true, dt:0.015});
             // sys.parameters(_maps[map_id].p);
//              $("#dataset").html(_maps[map_id].source);
            });
        return that;
      }
    };
    
    return that.init();
  }
  
  
  
  
	$(document).ready(function(){
	    var mcp = Maps("#maps");
	});
})();