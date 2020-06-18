# Bookmarks Manager :paperclip:

Application for convenient storing and organizing of bookmarks.

### Server

The server provides the following functionality:
- registering with username and password
- login
- bookmark storage 
- adding new bookmarks
- removing bookmarks
- grouping bookmarks in *collections*
- searching bookmarks by title or keywords

Bookmarks are stored in files on the server

Every added link has title corresponding to the title tag of the page.

When a new bookmark is added, the server extracts the keywords, so the user can search by them.

Keywords are most frequently met words.

### Client

The client has `command line interface` with the following commands:

```bash
register <username> <password>
login <username> <password>
make-collection <collection-name>
add <link> - add bookmark to default collection
add-to <collection> <link>
remove-from <collection> <link>
list-all - lists all of user's bookmarks
list <collection> - list all of the user's bookmarks in a given collection
search -tags <tag1> <tag2> <tag3> ...
search -title <title> - lists bookmarks with title <title>
import-from-chrome - adds all bookmarks from Google Chrome
```
