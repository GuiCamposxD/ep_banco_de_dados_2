<template>
	<v-dialog
		:model-value="true"
		max-width="1350"
		persistent
	>
		<v-card>
			<v-card-title>
				<v-container>
					<v-row>
						<v-col
							cols="11"
						>
							<h2>
                Indicados a Prêmios
              </h2>
						</v-col>

						<v-col
							cols="1"
							justify="end"
						>
							<v-btn
								variant="text"
								density="comfortable"
								icon="mdi-close"
								@click="closeModal"
							/>
						</v-col>
					</v-row>

          <v-row
            justify="center"
          >
            <v-col
              cols="6"
            >
              <v-autocomplete
								v-model="selectedAward"
								label="Prêmio"
								clearable
								item-title="nome"
								item-value="nome"
								return-object
                hide-details
								:items="awards"
							/>
            </v-col>
          </v-row>
				</v-container>
			</v-card-title>

			<v-card-text>
				<v-container
          v-if="hasNominatedActors"
          fluid
        >
					<v-row
            justify="center"
          >
            <v-col
              cols="10"
            >
              <h2
                v-if="selectedAward"
                class="text-center mb-10"
              >
                Atores indicados ao {{ selectedAward.nome }}
              </h2>

              <v-data-table
                :items="actors"
                :headers="nominatedActorsTableheaders"
              >
                <template v-slot:item.premiado="{ item }">
                    {{ item.premiado ? 'Premiado' : 'Não Premiado' }}
                </template>
              </v-data-table>
            </v-col>
          </v-row>
				</v-container>

        <v-container
          v-if="hasNominatedMovies"
          fluid
        >
					<v-row
            justify="center"
          >
            <v-col
              cols="10"
            >
              <v-data-table
                :items="movies"
                :headers="nominatedMoviesTableheaders"
              >
                <template v-slot:item.premiado="{ item }">
                    {{ item.premiado ? 'Premiado' : 'Não Premiado' }}
                </template>
              </v-data-table>
            </v-col>
          </v-row>
				</v-container>
			</v-card-text>
		</v-card>
	</v-dialog>
</template>

<script>
import axios from 'axios'

export default {
  name: 'MostAwardedPerson',
  data()  {
    return {
      actors: [],
      awards: [],
      movies: [],
      selectedAward: null,
      nominatedActorsTableheaders: [
        { title: 'Filme', key: 'filme_titulo_original' },
        { title: 'Ator (Atriz)', key: 'pessoa_nome_art' },
        { title: 'Premio', key: 'premio_nome' },
        { title: 'Premiado', key: 'premiado'},
        { title: 'Tipo Prêmio', key: 'tipo'},
      ],
      nominatedMoviesTableheaders: [
        { title: 'Filme', key: 'filme_titulo_original' },
        { title: 'Premio', key: 'premio_nome' },
        { title: 'Premiado', key: 'premiado'},
        { title: 'Tipo Prêmio', key: 'tipo'},
      ],
    }
  },
  computed: {
    hasNominatedActors() {
      return this.actors.length != 0
    },
    hasNominatedMovies() {
      return this.movies.length != 0
    }
  },
  async mounted() {
    const awardsReponse = await axios.get('/premio')
		this.awards = awardsReponse.data
  },
  watch: {
    selectedAward: {
      async handler(award) {
        if (!award) {
          this.actors = []
          this.movies = []
          return
        }

        const reponseActors = await axios.get('/pessoa_indicada_premio', {
          params: {
            edicao_ano: award.edicao_ano,
            edicao_nome_evento: award.edicao_nome_evento,
            tipo: award.tipo,
          },
        })
        this.actors = reponseActors.data

        const reponseMovies = await axios.get('/filme_indicado_premio', {
          params: {
            edicao_ano: award.edicao_ano,
            edicao_nome_evento: award.edicao_nome_evento,
            tipo: award.tipo,
          },
        })
        this.movies = reponseMovies.data
      },
    },
  },
  methods: {
    closeModal() {
			this.$emit('closeModal')
    },
  },
}
</script>
