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
               10 Filmes com Maior Arrecadções
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
				</v-container>
			</v-card-title>

			<v-card-text>
				<v-container
          fluid
        >
					<v-row
            justify="center"
          >
            <v-col
              cols="10"
            >
              <Bar
                id="my-chart-id"
                :data="chartData"
                :options="chartOptions"
              />
            </v-col>
          </v-row>
				</v-container>
			</v-card-text>
		</v-card>
	</v-dialog>
</template>

<script>
import axios from 'axios'

import { Bar } from 'vue-chartjs'
import { Chart as ChartJS, Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale } from 'chart.js'

ChartJS.register(Title, Tooltip, Legend, BarElement, CategoryScale, LinearScale)

export default {
  name: 'MostAwardedPerson',
  components: {
    Bar,
  },
  data()  {
    return {
      movies: [],
    }
  },
  computed: {
      chartData() {
        return {
          labels: this.getLabels(),
          datasets: [
            { 
              data: this.getValues(),
              label: 'Arrecadações Primeiro Ano',
            },
          ]
        }
      },
      chartOptions() {
        return {}
      }
    },
  async mounted() {
    const responseMostGrossingMovies = await axios.get('/filme/maior_arrecadacao')
    this.movies = responseMostGrossingMovies.data
  },
  methods: {
    closeModal() {
			this.$emit('closeModal')
    },
    getLabels() {
      return this.movies.map((movie) => {
        return movie.titulo_original
      })
    },
    getValues() {
      return this.movies.map((movie) => {
        return movie.arrecadacao_prim_ano
      })
    },
  },
}
</script>
