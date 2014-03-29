/******************************************
 * YOUTUBE FUNCTIONS
 ******************************************/

// Keeps track of the end time
var stopTime = 10000;

/******************************************
 * Functions to load videos
 ******************************************/

// Loads a video using the YouTube ID
function load(id) {
  player.loadVideoById(id);
  stopTime = 10000;
}

// Skips to a certain time in the current video
function skipTo(min, sec) {
  player.seekTo((min*60+sec), true);
}

// Loads a video with the YouTube ID, starting
// and ending at the specific time
function playClip(id, sMin, sSec, eMin, eSec) {

  player.loadVideoById(id, (sMin*60+sSec));

  stopTime = (eMin*60+eSec);
}

/******************************************
 * Functions to track playback
 ******************************************/

// Watches for changes in player state
function onPlayerStateChange(event) {
  if (event.data == 1) {
    trackTime();
  }
  else {
    stopTracking();
  }
}

var intervalId;

// Continuously checks current time
function trackTime() {
  intervalId = window.setInterval(stopPlaying, 1000);
}

// Stops tracking the time
function stopTracking() {
  intervalId = window.clearInterval(intervalId);
}

// Stops the video when the stop time is reached
function stopPlaying() {
  if (player.getCurrentTime() >= stopTime) {
    player.stopVideo();
    stopTime = 10000;
  }
}