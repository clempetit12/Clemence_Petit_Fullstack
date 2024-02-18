import { StyleSheet, Text, View, SafeAreaView, FlatList, TouchableOpacity, Image, Switch, ScrollView, SectionList } from 'react-native'
import React from 'react'
import { useEffect } from 'react'
import { useSelector, useDispatch } from 'react-redux';
import { fetchPokemons, setSelectedPokemon } from './PokemonSlice';
import CardPokemon from '../components/CardPokemon';


export default function Pokedex({ navigation }) {

    const pokemons = useSelector(state => state.pokemons.pokemons)
    const selectedPokemon = useSelector(state => state.pokemons.selectedPokemon)
    const dispatch = useDispatch()

  
    useEffect(() => {
        dispatch(fetchPokemons())
        console.log("tab pokemons", pokemons)
    }, [dispatch])

    return (
        <SafeAreaView    >

            <FlatList
                data={pokemons}
                contentContainerStyle={styles.container}
                initialNumToRender={5}
                windowSize={10}
                extraData={pokemons}
                maxToRenderPerBatch={5}
                numColumns={2}
                renderItem={(item) => (

                   
                        <CardPokemon   navigation={navigation}
                        item={item.item}>

                        </CardPokemon>

                )}
                keyExtractor={(item) => item.mainData.name}
            />



        </SafeAreaView>
    )
}

const styles = StyleSheet.create({
containerList: {
    padding:10
},
    
    container: {
        justifyContent: 'center',
        alignItems: 'center',
        padding:4
        
        
    },

    column: {
        flexDirection: 'column',
    },
})
