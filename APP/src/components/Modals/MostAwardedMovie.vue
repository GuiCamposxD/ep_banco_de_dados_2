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
               10 Filmes Mais Premiados
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
              label: 'Número de Premiações',
            },
          ]
        }
      },
      chartOptions() {
        return {}
      }
    },
  async mounted() {
    const responseMostAwardedMovies = await axios.get('/filme/mais_premiados')
    this.movies = responseMostAwardedMovies.data
  },
  methods: {
    closeModal() {
			this.$emit('closeModal')
    },
    getLabels() {
      return this.movies.map((movie) => {
        return movie.filme_titulo_original
      })
    },
    getValues() {
      return this.movies.map((movie) => {
        return movie.total_premios
      })
    },
  },
}
</script>
