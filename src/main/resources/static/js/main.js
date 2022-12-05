function loadComments () {

    this.source = null;

    this.start = function () {

        var commentTable = document.getElementById("comments");

        this.source = new EventSource("/api/log");

        this.source.addEventListener("message", function (event) {

            // These events are JSON, so parsing and DOM fiddling are needed
            var comment = JSON.parse(event.data);

            var row = commentTable.getElementsByTagName("tbody")[0].insertRow(0);
            var cell0 = row.insertCell(0);
            var cell1 = row.insertCell(1);
            var cell2 = row.insertCell(2);
            var cell3 = row.insertCell(3);


            // cell0.className = "author-style";
            cell0.innerHTML = comment.id;

            var textArray = ["badge badge-danger", "badge badge-success", "badge badge-info", "badge badge-warning"]
            var randomIndex = Math.floor(Math.random() * textArray.length); 
            var randomElement = textArray[randomIndex];
            cell1.className = randomElement;
            cell1.innerHTML = comment.ipClient;

            // cell2.className = "text";
            cell2.innerHTML = comment.description;

            // cell4.className = "date";
            cell3.innerHTML = comment.time;

        });

        this.source.onerror = function () {
            this.close();
        };

    };

    this.stop = function() {
        this.source.close();
    }
}

comment = new loadComments();



/*
 * Register callbacks for starting and stopping the SSE controller.
 */
window.onload = function() {
    comment.start();
};
window.onbeforeunload = function() {
    comment.stop();
}

// setTimeout(function(){
//     window.location.reload();
// }, 3000);
