// first
/*
def cards = (1..52).collect { it }

def numCards = cards.size()

def shuffled = []

while (shuffled.size() < numCards) {
  int next = new Random().nextInt(cards.size())
  shuffled << cards.remove(next)
}

shuffled
*/


// second
/*
def cards = (1..52).collect { it }

def numCards = cards.size()

def shuffled = []

while (shuffled.size() < numCards) {
  int next = new java.security.SecureRandom().nextInt(cards.size())
  shuffled << cards.remove(next)
}

shuffled
*/


// third
/*
def cards = (1..52).collect { it }
def shuffled = cards.sort { new Random().nextInt() }
*/


// fourth
//(1..52).collect { it }.sort { new java.security.SecureRandom().nextInt() }


// fifth
/*
def cards = (1..52).collect { it }
Collections.shuffle(cards)
cards
*/


// sixth
(1..52).collect { it }.sort { UUID.randomUUID() }