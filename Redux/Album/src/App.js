import { useDispatch, useSelector } from 'react-redux'
import styles from "./album/Album.module.css"
import Modal from './shared/Modal'
import NavBar from './shared/NavBar'
import { setFormMode} from './album/albumSlice'

import { useEffect } from 'react'


import { fetchAlbums } from './album/albumSlice'
import AddAlbumForm from './album/AddAlbumForm'
import AlbumDisplay from './album/AlbumDisplay'

function App() {
  const user = useSelector(state => state.auth.user)
  const formMode = useSelector(state => state.albums.formMode)
  const albums = useSelector(state => state.albums.albums)
  const dispatch = useDispatch()
  const filteredAlbum = useSelector(state => state.albums.filteredAlbum)



  useEffect(() => {
    dispatch(fetchAlbums())
    console.log(albums);
  }, [])

  return (
    <>
      {formMode === "add"  && <Modal onClose={() => dispatch(setFormMode(""))}><AddAlbumForm/></Modal>}
      {formMode === "delete" && <Modal onClose={() => dispatch(setFormMode(""))}><AddAlbumForm/></Modal>}
      {formMode === "edit" && <Modal onClose={() => dispatch(setFormMode(""))}><AddAlbumForm/></Modal>}

      <header>
        <NavBar />
      </header>
      <main className='container'>
        <div className='row my-3'>
          <div className='col-10 offset-1 bg-dark rounded text-light p-3'>
            <div className='d-flex justify-content-between align-items-center'>
              <h3>Albums</h3>
              {user && <button className='btn btn-success' onClick={() => dispatch(setFormMode("add"))}>Add</button>}
            </div>
            <hr />
            <div className={styles.card}>
              { albums.length === 0 ? (
                <p>Il n'y a pas d'albums</p>
              ) : filteredAlbum ? filteredAlbum.map(album => <AlbumDisplay key={album.id} album={album} />) : albums.map(album => <AlbumDisplay key={album.id} album={album} /> )  }
           
             
            
            </div>
           
          </div>
        </div>
      </main>
    </>
  )
}

export default App