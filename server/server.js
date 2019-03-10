// var url = require("url");
let express = require('express');
let fs = require('fs');
let bodyParser = require('body-parser')
let app;
let leaderboard = require('./leaderboard.json');
function start() {
    app = express();


	app.use(bodyParser.urlencoded({     // to support URL-encoded bodies
	  extended: true
	}));
	app.use(bodyParser.json());

	app.get('/', function (req, res) {
		res.sendFile(__dirname + '/index.html')
	});
	app.get('/getLeaderBoard', function(req, res){
        res.send(leaderboard)
    });
	app.get('/recordScore', function(req, res){
	    let name = req.query.name;
        let score = parseInt(req.query.score);

	    if(name !== undefined && name.length <= 4 && !Number.isNaN(score)){
	        console.log("name: %s, score %d",name,score);
            leaderboard.scores.push({"name":name,"score":score})
            leaderboard.scores.sort(function(a,b){
                return b.score - a.score
            })
            //console.log(leaderboard.scores)
            if(leaderboard.scores.length > leaderboard.maxNumScores){
                leaderboard.scores.splice(leaderboard.maxNumScores)
            }
            fs.writeFileSync(__dirname + "/leaderboard.json", JSON.stringify(leaderboard));
        }
        else{
            console.log("bad name or score")
        }
	    res.send(leaderboard)
    });
	//app.get('/listRooms',requestHandlers.listRooms)

	let port = 8081;
	if(process.platform === "linux"){
		port = 4001
	}

	app.listen(port);






	console.log("Server has started");
}

exports.start = start;
