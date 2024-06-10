<template>
  <v-dialog
    max-width="800"
    :model-value="true"
    :persistent="true"
    @click:outside="closeModal"
  >
    <v-card>
      <v-card-title>
        <v-container>
          <v-row>
            <v-col
              cols="11"
            >
              <h2>
              Cadastrar Pessoas
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
        <v-container>
          <v-row>
            <v-col>
              <v-text-field
                v-model="artisticName"
                label="Nome Artistico"
                :clearable="true"
              />
            </v-col>

            <v-col>
              <v-text-field
                v-model="realName"
                label="Nome"
                :clearable="true"
              />
            </v-col>

            <v-col>
              <v-select
                v-model="gender"
                label="Sexo"
                :items="['Masculino', 'Feminino', 'Outros']"
              />
            </v-col>
          </v-row>

          <v-row>
            <v-col>
              <v-text-field
                v-model="birthYear"
                label="Ano de Nascimento"
                :clearable="true"
              />
            </v-col>

            <v-col>
              <v-text-field
                v-model="site"
                label="Site"
                :clearable="true"
              />
            </v-col>

            <v-col>
              <v-text-field
                v-model="startYear"
                label="Ano Inicio na Academia"
                :clearable="true"
              />
            </v-col>
          </v-row>

          <v-row>
            <v-col>
              <v-text-field
                v-model="workYears"
                label="Número Total de Anos Trabalhados"
                :clearable="true"
              />
            </v-col>

            <v-col>
              <v-select
                v-model="situation"
                label="Situação"
                :items="['Ativo', 'Inativo']"
              />
            </v-col>

            <v-col>
              <v-select
                v-model="role"
                label="Cargo"
                :items="roles"
                :multiple="true"
              />
            </v-col>
          </v-row>

          <v-row
            justify="end"
          >
            <v-col
              cols="2"
            >
              <v-btn
                variant="flat"
                color="#FA7654"
                @click="closeModal"
              >
                Cancelar
              </v-btn>
            </v-col>

            <v-col
              cols="auto"
            >
              <v-btn
                variant="flat"
                color="#FAC95F"
                @click="handleCreatePerson"
              >
                Cadastrar Pessoa
              </v-btn>
            </v-col>
          </v-row>
        </v-container>
      </v-card-text>
    </v-card>

    <v-snackbar
      v-model="shouldShowSnackBar"
      color="#FAC95F"
      elevation="24"
      :timeout="2000"
      location="center"
    >
      <p>{{ snackBarMessage }}</p>
  
      <template v-slot:actions>
        <v-btn
          color="pink"
          variant="text"
          @click="closeSnackbar"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-dialog>

</template>

<script>
import axios from 'axios'

export default {
  name: 'CreatePerson',
  emits: [
    'closeModal',
  ],
  data() {
    return {
      artisticName: '',
      birthYear: '',
      gender: null,
      realName: '',
      role: [],
      roles: ['Diretor', 'Produtor', 'Roteirista', 'Ator'],
      shouldShowSnackBar: false,
      site: '',
      situation: null,
      snackBarMessage: '',
      startYear: '',
      workYears: '',
    }
  },
  methods: {
    closeModal() {
      this.$emit('closeModal')
    },
    async handleCreatePerson() {
      const response = await axios.post('/pessoa', {
        artisticName: this.artisticName,
        birthYear: this.birthYear,
        gender: this.gender,
        realName: this.realName,
        roles: this.role,
        site: this.site,
        situation: this.situation,
        startYear: this.startYear,
        workYears: this.workYears,
      })

      if (response.status === 200) {
        this.snackBarMessage = 'Usuário cadastrado com sucesso'
        this.shouldShowSnackBar = true
      }

      if (response.status === 500) {
        this.shouldShowSnackBar = true
        this.snackBarMessage = 'Erro ao cadastrar usuário, verifique os campos!'
      }
    },
    closeSnackbar() {
      this.shouldShowSnackBar = false
    },
  }
}
</script>
