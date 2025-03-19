create table cidade
(
    cid_id   bigserial,
    cid_nome varchar(200) not null,
    cid_uf   varchar(2)   not null,
    constraint cidade_cid_id_pk primary key (cid_id),
    constraint cidade_cid_nome_unique unique (cid_nome)
);

insert into cidade (cid_nome, cid_uf)
values ('Cuiabá', 'MT');


create table endereco
(
    end_id              bigserial,
    end_tipo_logradouro varchar(50)  not null,
    end_logradouro      varchar(200) not null,
    end_numero          varchar(100) not null,
    end_bairro          varchar(100) not null,
    cid_id              bigint       not null,
    constraint endereco_end_id_pk primary key (end_id),
    constraint endereco_cid_id_fk foreign key (cid_id) references cidade (cid_id)
);

insert into endereco (end_tipo_logradouro, end_logradouro, end_numero, end_bairro, cid_id)
values ('RUA', 'Major Gama', '161', 'Dom Aquino', 1);

create table unidade
(
    unid_id    bigserial,
    unid_nome  varchar(200) not null,
    unid_sigla varchar(50)  not null,
    constraint unidade_unid_id_pk primary key (unid_id),
    constraint unidade_unid_nome_unique unique (unid_nome),
    constraint unidade_unid_sigla_unique unique (unid_sigla)
);

insert into unidade (unid_nome, unid_sigla)
values ('UNIDADE XPTO', 'XPTO');


create table unidade_endereco
(
    unid_id bigint not null,
    end_id  bigint not null,
    constraint unidade_endereco_unid_id_end_id_pk primary key (unid_id, end_id),
    constraint unidade_endereco_unid_id_fk foreign key (unid_id) references unidade (unid_id),
    constraint unidade_endereco_end_id_fk foreign key (end_id) references endereco (end_id)
);

insert into unidade_endereco (unid_id, end_id)
values (1, 1);

create table pessoa
(
    pes_id              bigserial,
    pes_nome            varchar(200) not null,
    pes_data_nascimento date         not null,
    pes_sexo            varchar(50)  not null,
    pes_mae             varchar(200),
    pes_pai             varchar(200),
    constraint pessoa_pes_id_pk primary key (pes_id)
);

insert into pessoa (pes_nome, pes_data_nascimento, pes_sexo, pes_mae, pes_pai)
values ('João Carlos Junior', '1990-10-10', 'MASCULINO', 'Maria Antonieta Senior', 'José Carambolas Senior');

create table foto_pessoa
(
    fp_id     bigserial,
    fp_data   timestamp   not null,
    fp_bucket varchar(50) not null,
    fp_hash   varchar(50) not null,
    fp_url    varchar(200),
    pes_id    bigint      not null,
    constraint foto_pessoa_fp_id_pk primary key (fp_id),
    constraint foto_pessoa_pes_id_fk foreign key (pes_id) references pessoa (pes_id)
);

insert into foto_pessoa (fp_data, fp_bucket, fp_hash, fp_url, pes_id)
values (CURRENT_DATE, 'desafio-seletivo-seplag-bucket', '72d2d119-9e33-42f9-929c-bb7eb54062d0',
        'http://localhost:9000/desafio-processo-seletivo-seplag-bucket/72d2d119-9e33-42f9-929c-bb7eb54062d0', 1);

create table pessoa_endereco
(
    pes_id bigint not null,
    end_id bigint not null,
    constraint pessoa_endereco_pes_id_end_id_pk primary key (pes_id, end_id),
    constraint pessoa_endereco_pes_id_fk foreign key (pes_id) references pessoa (pes_id),
    constraint pessoa_endereco_end_id_fk foreign key (end_id) references endereco (end_id)
);

insert into pessoa_endereco (pes_id, end_id)
values (1, 1);

create table servidor_temporario
(
    pes_id           bigint not null,
    st_data_admissao date   not null,
    st_data_demissao date   not null,
    constraint servidor_temporario_pes_id_pk primary key (pes_id),
    constraint servidor_temporario_pes_id_fk foreign key (pes_id) references pessoa (pes_id)
);

create table servidor_efetivo
(
    pes_id       bigint not null,
    se_matricula varchar(20),
    constraint servidor_efetivo_pes_id_pk primary key (pes_id),
    constraint servidor_efetivo_pes_id_fk foreign key (pes_id) references pessoa (pes_id),
    constraint servidor_efetivo_se_matricula_unique unique (se_matricula)
);

insert into servidor_efetivo (se_matricula, pes_id)
values ('1000', 1);

create table lotacao
(
    lot_id           bigserial,
    lot_data_lotacao date,
    lot_data_remocao date,
    lot_portaria     varchar(100),
    pes_id           bigint not null,
    unid_id          bigint not null,
    constraint lotacao_lot_id_pk primary key (lot_id),
    constraint lotacao_pes_id_fk foreign key (pes_id) references pessoa (pes_id),
    constraint lotacao_unid_id_fk foreign key (unid_id) references unidade (unid_id)
);

insert into lotacao (lot_data_lotacao, lot_portaria, pes_id, unid_id)
values (CURRENT_DATE, 'PORTARIA XPTO', 1, 1);