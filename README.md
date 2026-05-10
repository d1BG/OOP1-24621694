# Курсов проект по ООП-1: 
#### Проект 17. Музикален плейлист

## Available Commands: 
Generic: 
- `Help [<command name>]`
- `Exit`
- `Gen <playlistCount> <artistsCount> <songsPerArtist> <maxPlaysRand>`

Artist Command:
- `AddArtist <username> [<firstName>] [<lastName>]` 
- `ListArtists`
- `RemoveArtist <username>`

File Commands:
- `Close`
- `Open <FilePath>`
- `SaveAs <FilePath>`
- `Save`

Play History Commands:
- `Play <songId> [playlist=<playlistName>]`
- `Plays [from=<date>] [to=<date>] [playlist=<name>] [song=<songId>]`
- `LowActivity <from> <to> <thresholdPercent>`
- `TopPlaylists <n> [from=<date>] [to=<date>]`
- `TopTracks <n> [from=<date>] [to=<date>]`
- `TopArtists <n> [from=<date>] [to=<date>]`

Playlist Commands:
- `AddToPlaylist <playlistName> <songId> [pos=<n>]`
- `CreatePlaylist <name> [<description>]`
- `DeletePlaylist <name>`
- `DropPlaylist <playlistName>`
- `ListPlaylists`
- `MovePlaylist <playlistName> <fromPos> <toPos>`
- `RemoveFromPlaylist <playlistName> <songId>`
- `ShowPlaylist <playlistName>`
- `Shuffle <playlistName> [seed=<n>]`

Song Commands:
- `AddSong <title> <artist> <duration> [alubm=<album>] [year=<year>] [genre=<genre>]`
- `ListSongs [artist=<artist>] [genre=<genre>] [year=<year>]`
- `RemoveSong <songId>`
- `SongInfo <songId>`